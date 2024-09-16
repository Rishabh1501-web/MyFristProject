package com.rishabh.kotlinexample.RxJavaExampple



data class WinningHistoryRequest(
    val header:String,
    val user_id: String,
    val type: String
)

data class Transaction(
    val transaction_id: String,
    val amount: String,
    val status: String,
    val created_at: String
)

data class WinningHistoryResponse(
    val transaction: List<Transaction>
)
