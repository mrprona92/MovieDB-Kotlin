
package erikjhordanrey.android_kotlin_devises.data.repository

import android.arch.lifecycle.LiveData
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

interface Repository {
  fun getMovieList(): LiveData<List<Movie>>
}
