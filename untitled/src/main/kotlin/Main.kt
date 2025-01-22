package org.example

data class Human(var name: String, var age: Int) {
    fun getOlder() {
        age++
    }
}
data class CourseRecord(
    val name: String
    val yearCompleted: Int,
    val credits: Int,
    val grade: Double
)
class Student(name: String, age: Int) : Human(name, age) {
    private val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalWeightedGrades = filteredCourses.sumOf { it.grade * it.credits }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }
    fun minMaxGrades

}