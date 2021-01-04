package cn.edu.sicnu.fundiary

data class Diary(
    val title:String,
    val type:Int,//0 1 2 3 4
    val content:String,
    val time:String,
    val isOnLock:Int,//是否上锁
    val info:String//备用项
){
    override fun toString(): String {
        return super.toString()+title.toString()+type.toString()
    }
}