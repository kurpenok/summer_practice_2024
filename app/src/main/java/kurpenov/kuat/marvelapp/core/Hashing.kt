package kurpenov.kuat.marvelapp.core

import java.math.BigInteger
import java.security.MessageDigest

class Hashing {
    companion object {
        @JvmStatic
        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}
