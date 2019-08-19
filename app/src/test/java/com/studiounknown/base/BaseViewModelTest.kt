package com.studiounknown.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import com.studiounknown.testutils.RxImmediateSchedulerRule
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

abstract class BaseViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    open fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}
