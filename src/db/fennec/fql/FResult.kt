package db.fennec.fql

import com.google.common.collect.Multimap

data class FResult internal constructor(val data: Multimap<Key, FData>)
