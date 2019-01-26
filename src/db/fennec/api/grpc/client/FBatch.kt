package db.fennec.api.grpc.client

import db.fennec.fql.FData

data class FBatch(val data: FData, val field: String, val ns: String)
