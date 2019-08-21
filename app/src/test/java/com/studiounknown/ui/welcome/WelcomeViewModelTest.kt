package com.studiounknown.ui.welcome

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.studiounknown.base.BaseViewModelTest
import com.studiounknown.data.Repository
import org.junit.Test
import org.mockito.Mock

class WelcomeViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: WelcomeViewModel

    override fun setUp() {
        super.setUp()
        whenever(repository.isFirstRun()).thenReturn(true)
        viewModel = WelcomeViewModel(repository)
    }

    @Test
    fun `When save first run, repo must update`() {
        viewModel.saveFirstRun()

        verify(repository).saveFirstRun()
    }
}
