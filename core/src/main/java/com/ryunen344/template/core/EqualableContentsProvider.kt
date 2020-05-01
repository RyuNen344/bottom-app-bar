package com.ryunen344.template.core

/**
 * https://qiita.com/takahirom/items/4125d7c871fad534d3c2#%E5%A4%89%E6%9B%B4%E3%82%A2%E3%83%8B%E3%83%A1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E5%95%8F%E9%A1%8C-equals--hashcode%E3%82%92%E7%84%A1%E7%90%86%E3%81%AA%E3%81%8F%E5%AE%9F%E8%A3%85%E3%81%99%E3%82%8B
 */
interface EqualableContentsProvider {
    fun providerEqualableContents(): Array<*>

    // equals()とhashCode()の実装を強制する
    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    fun isSameContents(other: Any?): Boolean {
        other ?: return false
        if (other !is EqualableContentsProvider) return false
        if (other::class != this::class) return false
        return other.providerEqualableContents().contentDeepEquals(this.providerEqualableContents())
    }

    fun contentsHash(): Int {
        return arrayOf(this::class, this.providerEqualableContents()).contentDeepHashCode()
    }
}
