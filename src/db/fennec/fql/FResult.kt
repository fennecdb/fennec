package db.fennec.fql

import com.google.common.collect.Multimap
import db.fennec.api.grpc.client.FennecGrpcClient

data class FResult internal constructor(val data: Multimap<FennecGrpcClient.Companion.Key, FData>)
