package com.slimakoi.rainbow.stuff

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by Asdev on 03/23/17. All rights reserved.
 * Unauthorized copying via any medium is stricitly
 * prohibited.
 *
 * Authored by Shahbaz Momi as part of ExpressionSolver
 * under the package com.asdev.expr
 */

/**
 * Represents an operation.
 */
enum class Operation {

    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    POWER,
    MODULO,
    GT,
    LT,
    EQUALITY,
    AND,
    OR,
    ROOT,
    FACTORIAL;

    /**
     * Returns whether or not this operation requires a value to the left of it.
     */
    fun requireLeftValue(): Boolean {
        return when(this) {
            FACTORIAL -> true
            else -> true
        }
    }

    /**
     * Returns whether or not this operation requires a value to the right of it.
     */
    fun requireRightValue(): Boolean {
        return when(this) {
            FACTORIAL -> false
            else -> true
        }
    }

    /**
     * Performs this operation and returns the result.
     */
    fun execute(a: Float, b: Float): Float {
        when {
            this == PLUS -> {
                return a + b
            }
            this == MINUS -> {
                return a - b
            }
            this == MULTIPLY -> {
                return a * b
            }
            this == DIVIDE -> {
                return a / b
            }
            this == POWER -> {
                return a.toDouble().pow(b.toDouble()).toFloat()
            }
            this == MODULO -> {
                return a % b
            }
            this == GT -> {
                return if(a > b) 1f else 0f
            }
            this == LT -> {
                return if(a < b) 1f else 0f
            }
            this == EQUALITY -> {
                return if(a == b) 1f else 0f
            }
            this == AND -> {
                return if((a > 0f) && (b > 0f)) 1f else 0f
            }
            this == OR -> {
                return if((a > 0f) || (b > 0f)) 1f else 0f
            }
            this == ROOT -> {
                return when (a) {
                    2f -> {
                        sqrt(b.toDouble()).toFloat()
                    }
                    3f -> {
                        Math.cbrt(b.toDouble()).toFloat()
                    }
                    else -> {
                        val factor = 1.0 / a // 4^0.5 = 2 root 4, 8^0.3333... = 3 root 8
                        b.toDouble().pow(factor).toFloat()
                    }
                }
            }
            this == FACTORIAL -> {
                if(a > 34f) {
                    throw IllegalArgumentException("Factorial number too high: $a")
                }

                return factorial(a.toInt())
            }
            else -> throw IllegalArgumentException("Unknown operation: $name")
        }

    }

    private fun factorial(i: Int): Float {
        var result = 1f
        for(j in 1..i) {
            result *= j.toFloat()
        }

        return result
    }
}

fun getOperationForChar(c: Char): Operation? {
    when (c) {
        '+' -> {
            return Operation.PLUS
        }
        '-' -> {
            return Operation.MINUS
        }
        '/' -> {
            return Operation.DIVIDE
        }
        '*' -> {
            return Operation.MULTIPLY
        }
        '^' -> {
            return Operation.POWER
        }
        '%' -> {
            return Operation.MODULO
        }
        '>' -> {
            return Operation.GT
        }
        '<' -> {
            return Operation.LT
        }
        '=' -> {
            return Operation.EQUALITY
        }
        '&' -> {
            return Operation.AND
        }
        '|' -> {
            return Operation.OR
        }
        '√' -> {
            return Operation.ROOT
        }
        '!' -> {
            return Operation.FACTORIAL
        }
        else -> return null
    }

}

fun isOperator(c: Char) = getOperationForChar(c) != null