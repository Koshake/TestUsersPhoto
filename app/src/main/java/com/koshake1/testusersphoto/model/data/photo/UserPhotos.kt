package com.koshake1.testusersphoto.model.data.photo

import com.koshake1.testusersphoto.model.data.viewstate.AppStateEntity

data class UserPhotos(
    val photos : List<Photo>
) : AppStateEntity
