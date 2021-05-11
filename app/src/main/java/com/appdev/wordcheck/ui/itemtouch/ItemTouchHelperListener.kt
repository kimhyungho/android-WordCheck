package com.appdev.wordcheck.ui.itemtouch

interface ItemTouchHelperListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}