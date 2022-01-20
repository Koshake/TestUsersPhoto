package com.koshake1.testusersphoto.model.datasources

import com.koshake1.testusersphoto.model.api.ApiServiceImage

class ImageDataSourceImpl(private val apiServiceImage: ApiServiceImage) : ImageDataSource {

    override suspend fun getImage(imageUrl : String) =
        apiServiceImage.getImage(imageUrl).await()
}