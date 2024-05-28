package com.example.logique_test.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.logique_test.data.datasources.user.UserRemoteDataSource
import com.example.logique_test.domain.mapper.toUiModel
import com.example.logique_test.domain.uimodel.UserUiModel
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    private val remoteDataSource: UserRemoteDataSource,
) : PagingSource<Int, UserUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserUiModel> {
        return try {
            val currentPage = params.key ?: 1
            val users = remoteDataSource.getUserList(
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

    override fun getRefreshKey(state: PagingState<Int, UserUiModel>): Int? {
        return state.anchorPosition
    }

}