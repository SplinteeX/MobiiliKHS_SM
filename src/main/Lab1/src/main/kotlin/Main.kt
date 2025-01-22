open class Human(var name: String, var age: Int) {
    fun getOlder() {
        age++
    }
}

data class CourseRecord(
    val name: String,
    val yearCompleted: Int,
    val credits: Int,
    val grade: Double
)

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalWeightedGrades = courses.sumOf { it.grade * it.credits }
        val totalCredits = courses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalWeightedGrades = filteredCourses.sumOf { it.grade * it.credits }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        return if (grades.isNotEmpty()) {
            grades.minOrNull()!! to grades.maxOrNull()!!
        } else {
            0.0 to 0.0
        }
    }
}

class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        val min = averages.minOrNull() ?: 0.0
        val max = averages.maxOrNull() ?: 0.0
        val avg = if (averages.isNotEmpty()) averages.average() else 0.0
        return Triple(min, max, avg)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val averages = students.mapNotNull {
            it.courses.filter { course -> course.name == courseName }
                .takeIf { it.isNotEmpty() }
                ?.let { courses ->
                    val totalWeightedGrades = courses.sumOf { course -> course.grade * course.credits }
                    val totalCredits = courses.sumOf { course -> course.credits }
                    if (totalCredits > 0) totalWeightedGrades / totalCredits else null
                }
        }
        val min = averages.minOrNull() ?: 0.0
        val max = averages.maxOrNull() ?: 0.0
        val avg = if (averages.isNotEmpty()) averages.average() else 0.0
        return Triple(min, max, avg)
    }
}
