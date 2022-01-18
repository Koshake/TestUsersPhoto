package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource

class PhotosRepositoryImpl(private val dataSource : RemoteDataSource) : PhotosRepository {

    override suspend fun getAlbums(userId: Int): List<UserAlbums> =
        dataSource.getAlbums(userId)

    override suspend fun getPhotos(albumId: Int): List<Photo> =
        dataSource.getPhotos(albumId)
}