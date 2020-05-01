package com.ryunen344.template.repository.github

import com.ryunen344.template.api.github.GitHubApi
import com.ryunen344.template.api.github.response.OwnerResponse
import com.ryunen344.template.api.github.response.RepositoryItemResponse
import com.ryunen344.template.model.Owner
import com.ryunen344.template.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ImplicitReflectionSerializer

class GitHubRepository(private val gitHubApi: GitHubApi) {

    @ImplicitReflectionSerializer
    fun getRepositoryList(since: Int?): Flow<List<Repository>> = flow {
        emit(
            gitHubApi
                .getRepositories(since)
                .repositories
                .map(RepositoryItemResponse::toRepository)
        )
    }
}

private fun RepositoryItemResponse.toRepository(): Repository =
    Repository(
        id = id,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner(),
        htmlUrl = htmlUrl,
        description = description,
        fork = fork,
        url = url,
        forksUrl = forksUrl,
        keysUrl = keysUrl,
        collaboratorsUrl = collaboratorsUrl,
        teamsUrl = teamsUrl,
        hooksUrl = hooksUrl,
        issueEventsUrl = issueEventsUrl,
        eventsUrl = eventsUrl,
        assigneesUrl = assigneesUrl,
        branchesUrl = branchesUrl,
        tagsUrl = tagsUrl,
        blobsUrl = blobsUrl,
        gitTagsUrl = gitTagsUrl,
        gitRefsUrl = gitRefsUrl,
        treesUrl = treesUrl,
        statusesUrl = statusesUrl,
        languagesUrl = languagesUrl,
        stargazersUrl = stargazersUrl,
        contributorsUrl = contributorsUrl,
        subscribersUrl = subscribersUrl,
        subscriptionUrl = subscriptionUrl,
        commitsUrl = commitsUrl,
        gitCommitsUrl = gitCommitsUrl,
        commentsUrl = commentsUrl,
        issueCommentUrl = issueCommentUrl,
        contentsUrl = contentsUrl,
        compareUrl = compareUrl,
        mergesUrl = mergesUrl,
        archiveUrl = archiveUrl,
        downloadsUrl = downloadsUrl,
        issuesUrl = issuesUrl,
        pullsUrl = pullsUrl,
        milestonesUrl = milestonesUrl,
        notificationsUrl = notificationsUrl,
        labelsUrl = labelsUrl,
        releasesUrl = releasesUrl,
        deploymentsUrl = deploymentsUrl
    )

private fun OwnerResponse.toOwner(): Owner =
    Owner(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        siteAdmin = siteAdmin
    )
