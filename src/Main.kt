var data: MutableMap<String, String> = mutableMapOf()

fun add(params: List<String>) {
    try {
        data[params[0]] = params[1]
    } catch (e: IndexOutOfBoundsException) {
        println("Params are not valid")
    }
}

fun del(params: List<String>, c: Char) {
    try {
        when (c) {
            'a' -> data.remove(params[0], params[1])
            'k' -> data.remove(params[0])
            'v' -> data = data.filterValues { it != params[0] }.toMutableMap()
        }
    } catch (e: IndexOutOfBoundsException) {
        println("Params are not valid")
    }
}

fun print_pairs(params: List<String>, c: Char) {
    try {
        when (c) {
            'a' -> println(data.filter { it.key.indexOf(params[0]) > -1 && it.value.indexOf(params[1]) > -1 })
            'k' -> println(data.filter { it.key.indexOf(params[0]) > -1 })
            'v' -> println(data.filter { it.value.indexOf(params[0]) > -1 })
        }
    } catch (e: IndexOutOfBoundsException) {
        println("Params are not valid")
    }
}


fun main(args: Array<String>) {
    println("""
        Hello.
        You can use this commands:
            exit - stop program
            add x y - add pair with key x and value y
            del x y - delete pair with key x and value y
            del_k x - delete pair by key x
            del_v x - delete pair by value x
            find x y - find pair with key x and value y
            find_k x - find pair by key x
            find_v x - find pair by value x
            all - list all pairs
    """.trimIndent())
    do {
        print("> ")
        var params = readLine()!!.split(' ')
        val cmd = params[0]
        params = params.subList(1, params.size)
        when (cmd) {
            "add" -> add(params)
            "del" -> del(params, 'a')
            "del_k" -> del(params, 'k')
            "del_v" -> del(params, 'v')
            "find" -> print_pairs(params, 'a')
            "find_k" -> print_pairs(params, 'k')
            "find_v" -> print_pairs(params, 'v')
            "all" -> println(data)
            else -> println("Command not recognized")
        }
    } while (cmd != "exit")
}
