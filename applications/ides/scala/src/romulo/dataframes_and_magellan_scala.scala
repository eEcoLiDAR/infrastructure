import geotrellis.pointcloud.spark.io.hadoop.HadoopPointCloudRDD
import io.pdal.Pipeline
import io.pdal.pipeline.LasRead
import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}


object pdal_pointclouds extends App {

  override def main(args: Array[String]): Unit = {
    val appName = this.getClass.getName
    val masterURL = "spark://emma0.emma.nlesc.nl:7077"
    val sc = new SparkContext(new SparkConf().setAppName(appName).setMaster(masterURL))

    //DATA PATHS
    val tiles_path = "/user/hadoop/ahn3/"
    val tile = "C_25EZ2"
    val las_filepath = tiles_path + tile + ".las"
    val laz_filepath = tiles_path + tile + ".laz"

    //READ LOCAL
    val las_pipelineExpr = LasRead(las_filepath)
    val las_pipeline: Pipeline = las_pipelineExpr.toPipeline
    las_pipeline.execute()

    val laz_pipelineExpr = LasRead(laz_filepath)
    val laz_pipeline: Pipeline = laz_pipelineExpr.toPipeline
    laz_pipeline.execute()

    //READ HDFS
    val las_path = new Path("/user/hadoop/ahn3/C_25EZ2.las")
    val laz_path = new Path("/user/hadoop/ahn3/C_25EZ2.laz")

    val rdd_las = HadoopPointCloudRDD(las_path)
    rdd_las.count()

    val rdd_laz = HadoopPointCloudRDD(laz_path)
    rdd_laz.count()
  }
}