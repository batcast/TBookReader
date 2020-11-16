package com.example.tbookreader.help

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlin.coroutines.CoroutineContext

class Coroutine<T>(
        val scope: CoroutineScope,
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> T
) {
    companion object {
        val DEFAULT = MainScope()

        fun <T> async(
                scope: CoroutineScope = DEFAULT,
                context: CoroutineContext = Dispatchers.IO,
                block: suspend CoroutineScope.() -> T
        ): Coroutine<T> = Coroutine(scope, context, block)
    }
}