package tech.stdy.studysearch

import tech.stdy.studysearch.document.*

class GroupMaker {
    fun makeGroups(student: Student, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): Map<StudentClass, List<StudyGroup>> {
        val potentialGroups = mutableMapOf<StudentClass, ArrayList<StudyGroup>>()
        for (section in student.classes!!) {
            val groups = makeClassGroups(student, section, studyGroupRepo, studentRepo)
            potentialGroups[section] = groups as ArrayList<StudyGroup>
        }
        return potentialGroups
    }

    private fun makeClassGroups(student: Student, section: StudentClass, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): List<StudyGroup> {
        // val groups =


        val potentialGroups = mutableListOf<StudyGroup>()
        // for (group in groups) {
        //     if (group.students.size < 4) {
        //         potentialGroups.add(group)
        //     }
        // }
        return potentialGroups
    }

    private fun makeGroupRec(members: List<Student>, section: StudentClass, memberLimit: Int, studyGroupRepo: StudyGroupRepository, studentRepo: StudentRepository): StudyGroup? {
        val availability = computeWeeklyAvailability(members)
        // if member list is not free, return null
        if (availability.map { it.value.size }.sum() == 0) {
            return null
        }
        // if member list is available, make group
        // if (members.size == memberLimit) {
        //     return StudyGroup(section, members)
        // }
        // // if group is full, return group
        // // if group is not full, recurse
        // val group = StudyGroup(section, members)
        return null
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
