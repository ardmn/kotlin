interface X
interface Y

operator fun X.iterator(): Y
operator fun Y.next(): Int
operator fun Y.hasNext(): Boolean


fun foo(x: X, y: Y) {
    for (i in <caret>)
}

// EXIST: x
// ABSENT: y
