interface GithubRepository {

    suspend fun fetchRepositories(username: String) : List<ApiRepository>

}