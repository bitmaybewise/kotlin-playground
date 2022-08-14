import java.util.Calendar

data class User(val email: String)

object store {
    private val users = mutableListOf<User>()

    fun add(user: User) {
        users.add(user)
    }

    fun find(email: String): User? {
        for (user in users) {
            if (user.email == email) {
                return user
            }
        }
        return null
    }
}


fun timeTaken(fn: () -> Unit): Long {
    val before = Calendar.getInstance().timeInMillis
    fn()
    val after = Calendar.getInstance().timeInMillis
    return after - before
}

fun main(args: Array<String>) {
    for (i in 0 until 50_000_000) {
        val user = User(email="someone@${i}")
        store.add(user)
    }

    val milliseconds = timeTaken {
        val notFound = store.find("not@found")?.email ?: "not found"
        println("user ${notFound}")
        // user not found
    }
    println("time taken = ${milliseconds}")
    // time taken = 389
}