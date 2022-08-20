import java.util.Calendar
import java.util.TreeMap

data class User(val email: String)

object store {
    private val users = mutableListOf<User>()
    private val emailIndex = TreeMap<String, Int>()

    fun add(user: User) {
        users.add(user)
        emailIndex.put(user.email, users.size-1)
    }

    fun find(email: String): User? {
        val idx = emailIndex.get(email) ?: -1
        return if (idx > -1) users.get(idx)
               else null
    }
}


fun timeTaken(fn: () -> Unit): Long {
    val before = Calendar.getInstance().timeInMillis
    fn()
    val after = Calendar.getInstance().timeInMillis
    return after - before
}

fun main(args: Array<String>) {
    for (i in 0 until 30_000_000) {
        val user = User(email="someone@${i}")
        store.add(user)
    }

    val milliseconds = timeTaken {
        val notFound = store.find("not@found")?.email ?: "not found"
        println("user ${notFound}")
        // user not found
    }
    println("time taken = ${milliseconds}")
    // time taken = 22
}