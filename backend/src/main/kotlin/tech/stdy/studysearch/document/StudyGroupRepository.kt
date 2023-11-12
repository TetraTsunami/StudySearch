package tech.stdy.studysearch.document

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface StudyGroupRepository : MongoRepository<StudyGroup, String> {
    fun findStudyGroupsByMembersContaining(vararg members: ObjectId): List<StudyGroup>
}