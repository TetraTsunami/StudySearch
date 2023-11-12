package tech.stdy.studysearch

import tech.stdy.studysearch.document.*

class GroupMaker {
    fun makeGroups(student: Student, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): Map<StudentClass, List<StudyGroup>> {
        val potentialGroups = mutableMapOf<StudentClass, ArrayList<StudyGroup>>()
        for (section in student.classes!!) {
            val groups = filterGroups(student, section, studyGroupRepo, studentRepo)
            potentialGroups[section] = groups as ArrayList<StudyGroup>
        }
        return potentialGroups
    }

    private fun filterGroups(student: Student, section: StudentClass, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): List<StudyGroup> {
        val computedGroups = makeGroupsRec(listOf(student), section, 20, studyGroupRepo, studentRepo)
        val potentialGroups = mutableListOf<StudyGroup>()
        // this is where we implement user preferences
        if (computedGroups != null) {
            for (group in computedGroups) {
                if (group.members.size > 1) {
                    potentialGroups.add(group)
                }
            }
        }
        return potentialGroups
    }

    private fun makeGroupsRec(members: List<Student>, section: StudentClass, memberLimit: Int = 20, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): List<StudyGroup>? {
        val availability = computeWeeklyAvailability(members)
        // if members are not all free at the same time, return null
        if (availability.map { it.value.size }.sum() == 0) {
            return null
        }
        // if member list is available and full, return group
        if (members.size == memberLimit) {
            return listOf(StudyGroup(classId = section.id, members = members.map { it.id }, meetingTime = availability))
        }
        // if group is not full, add a member
        val potentialGroups = mutableListOf<StudyGroup>()
        for (student in studentRepo.findAll()) {
            if (student.classes!!.contains(section) && !members.contains(student)) {
                val newMembers = members.toMutableList()
                newMembers.add(student)
                val groups = makeGroupsRec(newMembers, section, memberLimit, studyGroupRepo, studentRepo);
                if (groups != null) {
                    potentialGroups.addAll(groups)
                }
            }
        }
        // if there are no groups which are a superset of this group, return this group
        // if there are groups which are a superset of this group, return those groups
        return if (potentialGroups.size == 0) {
            listOf(StudyGroup(classId = section.id, members = members.map { it.id }, meetingTime = availability))
        } else {
            potentialGroups
        }
    }

    private fun computeWeeklyAvailability(members: List<Student>): WeekSchedule {
        val weeklyAvailability = members[0].availability!!.toMutableMap()
        for (day in Day.values()) {
            var dailyAvailability = weeklyAvailability[day]!!.toMutableList()
            for (member in members) {
                for (block in member.availability!![day]!!) {
                    val intersection = mutableListOf<DaySchedule>()
                    for (block2 in dailyAvailability) {
                        if (block.overlaps(block2)) {
                            block2.intersection(block)?.let { intersection.add(it) }
                        }
                    }
                    dailyAvailability = intersection
                }
            }
            weeklyAvailability[day] = dailyAvailability
        }
        return weeklyAvailability
    }
}
