package net.dean.jraw.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.dean.jraw.databind.DistinguishedStatusDeserializer
import net.dean.jraw.databind.UnixTimeDeserializer
import java.util.*

data class Message(
    /** The full name of the message that kicked off this private message thread, or null if this message isn't a PM */
    @JsonProperty("first_message_name")
    val firstMessage: String?,

    /** The name of the user that created this message */
    val author: String,

    /** The markdown-formatted message content */
    val body: String,

    /** If this message is for a comment, the permalink to said comment with the query "`?context=3`"  */
    val context: String,

    @JsonDeserialize(using = UnixTimeDeserializer::class)
    override val created: Date,

    /** The name of the user that started the conversation */
    val dest: String,

    @JsonDeserialize(using = DistinguishedStatusDeserializer::class)
    override val distinguished: DistinguishedStatus?,

    @JsonProperty("name")
    override val fullName: String,
    override val id: String,

    /** True if this message represents a reply to a comment made by the logged-in user */
    @JsonProperty("was_comment")
    val isComment: Boolean,

    override val likes: Boolean?,

    /** If this message has not been marked as read */
    val new: Boolean,

    /** The total amount of comments in the submission, or null if this message is not for a comment */
    val numComments: Int?,

    /** The full name of the comment or PM that is the predecessor to this one. */
    val parentId: String?,

    override val score: Int,

    /** The subject line of the private message, or the string "`comment reply`" for comments. */
    val subject: String,

    /**
     * The name of the subreddit where this comment or username mention was created, or null if this message isn't a
     * comment or username mention
     */
    val subreddit: String?
) : RedditObject(KindConstants.MESSAGE), Created, Distinguishable, Identifiable, Votable
