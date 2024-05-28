package com.example.logique_test.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.logique_test.data.datasources.PostRemoteDataSource
import com.example.logique_test.data.datasources.user.UserRemoteDataSource
import com.example.logique_test.domain.mapper.toUiModel
import com.example.logique_test.domain.uimodel.PostUiModel
import com.example.logique_test.domain.uimodel.UserUiModel
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val userId: String,
    private val remoteDataSource: PostRemoteDataSource,
) : PagingSource<Int, PostUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostUiModel> {
        return try {
            val currentPage = params.key ?: 1
            val users = remoteDataSource.getUserPost(
                userId = userId,
                page = currentPage
            )

            LoadResult.Page(
                data = users.data.map { it.toUiModel() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (users.data.isEmpty()) null else users.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostUiModel>): Int? {
        return state.anchorPosition
    }

}