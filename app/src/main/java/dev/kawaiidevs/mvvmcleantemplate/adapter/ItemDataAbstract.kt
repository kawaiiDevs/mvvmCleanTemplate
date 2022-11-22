package dev.kawaiidevs.mvvmcleantemplate.adapter

class ItemDataAbstract<T>(
    override val data: T,
    override val type: Int = 0
) : ItemData<T>