package segerfast.philip.whattodo.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import segerfast.philip.whattodo.data.AppDatabase
import segerfast.philip.whattodo.data.TodoTasksDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase): TodoTasksDao {
        return appDatabase.todoDao()
    }
}
