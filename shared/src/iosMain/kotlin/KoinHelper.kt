import com.jgpl.kmpmovies.di.sharedModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModule())
    }
}