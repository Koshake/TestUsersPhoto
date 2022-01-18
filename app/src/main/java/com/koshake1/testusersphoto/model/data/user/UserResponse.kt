package com.koshake1.testusersphoto.model.data.user

import com.koshake1.testusersphoto.model.data.viewstate.AppStateEntity

data class UserResponse(
    val users : List<User>
) : AppStateEntity
