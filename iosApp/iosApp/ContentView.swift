import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        List(viewModel.movies, id: \.self) { movie in
            Text(movie)
        }.onAppear {
            viewModel.loadMovies()
        }
    }
}


extension ContentView {
    class ViewModel: ObservableObject {
        @Published var movies: [String] = []

        func loadMovies() {
            let helper = GetMoviesPopularUseCaseHelper()
            helper.execute { movies in
                DispatchQueue.main.async {
                    self.movies = movies.map { $0.title }
                }
            }
        }
    }
}
