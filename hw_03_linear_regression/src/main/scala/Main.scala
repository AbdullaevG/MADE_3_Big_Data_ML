import breeze.linalg.{DenseMatrix, DenseVector, csvread, csvwrite}
import models.LinearRegression
import models.utils.cross_validation
import models.utils.get_MAE
import java.io.File


object Main {

   // Advertising Dataset : https://www.kaggle.com/datasets/ashydv/advertising-dataset


  def main(args: Array[String]): Unit = {

    // path to train, test and to output file for prediction

    val args = Seq("C:/Users/asus/IdeaProjects/linear_regression/src/main/resource/train_data.csv",
                    "C:/Users/asus/IdeaProjects/linear_regression/src/main/resource/test_data.csv",
                    "C:/Users/asus/IdeaProjects/linear_regression/src/main/resource/predicts.csv")


    val train_path: File = new File(args(0))
    val test_path: File = new File(args(1))
    val predict_path: File = new File(args(2))

    // get train and test data
    val train_data: DenseMatrix[Double] = csvread(train_path, separator = ',', skipLines = 1)
    val test_data: DenseMatrix[Double] = csvread(test_path, separator = ',', skipLines = 1)

    val x_train: DenseMatrix[Double] = train_data(::, 0 to -2)
    val y_train: DenseVector[Double] = train_data(::, -1)
    val x_test: DenseMatrix[Double] = test_data(::, 0 to -2)
    val y_test: DenseVector[Double] = test_data(::, -1)

    val model = new LinearRegression()

    // k-fold cross_validation
    cross_validation(model, train_data, folds = 5)

    // training using all data:
    model.fit(x_train, y_train)

    // predict and save result
    val y_predict: DenseVector[Double] = model.predict(x_test)
    val mae_on_test: Double = get_MAE(y_test, y_predict)

    println("")
    println(s"MAE on test: $mae_on_test")
    csvwrite(predict_path, y_predict.asDenseMatrix.t)
    }

  }
