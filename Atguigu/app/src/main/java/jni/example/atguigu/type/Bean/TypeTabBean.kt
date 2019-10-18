package jni.example.atguigu.type.Bean

data class TypeTabBean(
    val code: Int,
    val msg: String,
    val result: List<Result>
)

data class Result(
    val name: String,
    val tag_id: String
)