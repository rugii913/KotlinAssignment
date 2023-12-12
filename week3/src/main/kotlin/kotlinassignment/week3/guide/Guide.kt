package kotlinassignment.week3.guide

import kotlinassignment.week3.messenger.ContinueState

interface Guide {

    fun guide(continueState: ContinueState)
}