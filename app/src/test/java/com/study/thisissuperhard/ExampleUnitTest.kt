package com.study.thisissuperhard

import com.study.thisissuperhard.todolist.MockGetTodosUseCase
import com.study.thisissuperhard.todolist.TodoListViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun test_ToDoListViewModel_Should_Exist() {
//        var vm = TodoListViewModel(
//            getTodos = MockGetTodosUseCase(),
//        )
//
//        assert(vm != null)
//    }
}