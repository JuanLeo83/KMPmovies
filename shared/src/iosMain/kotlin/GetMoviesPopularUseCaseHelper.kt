import com.jgpl.kmpmovies.domain.model.Movie
import com.jgpl.kmpmovies.domain.usecase.GetMoviesPopularUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesPopularUseCaseHelper : KoinComponent {

    private val getMoviesPopularUseCase: GetMoviesPopularUseCase by inject()

    @OptIn(DelicateCoroutinesApi::class)
    fun execute(callback: (List<Movie>) -> Unit) {
        GlobalScope.launch {
            val result = getMoviesPopularUseCase()
            val movies = if (result.isSuccess) {
                result.getOrNull() ?: emptyList()
            } else {
                emptyList()
            }
            callback(movies)
        }
    }

}