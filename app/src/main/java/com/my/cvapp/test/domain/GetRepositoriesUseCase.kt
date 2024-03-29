class GetRepositoriesUseCase(private val apiRepository: GithubRepository) {
    suspend fun execute(username: String): List<Repository> {
        return this.apiRepository.fetchRepositories(username).map { Repository(it.id, it.name) }
    }
}