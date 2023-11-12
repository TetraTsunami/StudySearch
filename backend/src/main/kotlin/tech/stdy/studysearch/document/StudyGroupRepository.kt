package tech.stdy.studysearch.document

import org.springframework.data.mongodb.repository.MongoRepository

interface StudyGroupRepository : MongoRepository<StudyGroup, String>