package de.muspellheim.userstories.message

interface Message
interface Incoming : Message
interface Outcoming : Message

interface Request : Incoming
interface Response : Outcoming

interface Notification : Incoming, Outcoming

interface Command : Request
interface Query : Request

interface CommandStatus : Response

class Success : CommandStatus
class Failure(errorMessage: String) : CommandStatus

interface QueryResult : Response

object NoResponse : Response
