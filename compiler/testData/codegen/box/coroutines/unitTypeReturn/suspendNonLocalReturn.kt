// WITH_RUNTIME
// WITH_COROUTINES
import kotlin.coroutines.*

var result = "0"


suspend fun suspendHere(x: Int): Unit {
    run {
        if (x == 0) return
        if (x == 1) return@suspendHere
    }

    result = "OK"
    return CoroutineIntrinsics.suspendCoroutineOrReturn { x ->
        x.resume(Unit)
        CoroutineIntrinsics.SUSPENDED
    }
}

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    builder {
        if (suspendHere(0) != Unit) throw RuntimeException("fail 1")
        if (suspendHere(1) != Unit) throw RuntimeException("fail 2")
        if (suspendHere(2) != Unit) throw RuntimeException("fail 3")
    }

    return result
}
