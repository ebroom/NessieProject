package org.hacking.nessieproj

class MessageText {
    companion object {
        const val DEFAULT = "Something went wrong...Please try later!"
        const val SUCCESS = "Request was successful!"
        const val INVALID = "Invalid request"
        const val SERVER_ERROR = "Server error...Please try later!"

        fun getMessageFromApiCode(code: Int?): String {
            var message = MessageText.DEFAULT
            when(code) {
                2 -> message = MessageText.SUCCESS
                4 -> message = MessageText.INVALID
                5 -> message = MessageText.SERVER_ERROR
            }
            return message
        }
    }
}