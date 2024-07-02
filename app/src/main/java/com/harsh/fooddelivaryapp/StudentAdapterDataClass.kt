package com.harsh.fooddelivaryapp

data class StudentAdapterDataClass(
    var etItem: String? = "",
    var etQnty: Int? = 0,
)
{
    override fun toString(): String {
        return "$etItem"
    }
}
