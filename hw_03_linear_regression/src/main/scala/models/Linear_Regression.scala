package models

import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.numerics.abs
import breeze.stats.mean



class LinearRegression {
  var matrix: DenseVector[Double] = DenseVector.zeros[Double](size = 0)

  def fit(features: DenseMatrix[Double], outputs: DenseVector[Double]): Unit = {
    /**
     * Given a set of data points as rows in a matrix and their corresponding outputs, produces a vector of weights
     * s.t. output(i) \approx observations(i) dot weights
     */
    val cov = features.t * features
    val scaled = features.t * outputs
    matrix = cov \ scaled
  }

  def predict(features: DenseMatrix[Double]): DenseVector[Double] = {
    features * matrix
  }
}

package object utils {

   def get_MAE(y_true: DenseVector[Double], y_pred: DenseVector[Double]): Double = {
    mean(abs(y_true - y_pred))
    }

  def cross_validation(model: LinearRegression, data: DenseMatrix[Double], folds: Int): Unit = {
    val step: Int = data.rows / folds

    for (i <- 0 until folds) {
      val train_idx: IndexedSeq[Int] = IndexedSeq.range(0, i * step) ++ IndexedSeq.range((i + 1) * step, data.rows)
      val valid_idx: IndexedSeq[Int] = IndexedSeq.range(i * step, (i + 1) * step)

      val train_fold: DenseMatrix[Double] = data(train_idx, ::).toDenseMatrix
      val x_train: DenseMatrix[Double] = train_fold(::, 0 to -2)
      val y_train: DenseVector[Double] = train_fold(::, -1)

      val valid_fold: DenseMatrix[Double] = data(valid_idx, ::).toDenseMatrix
      val x_valid: DenseMatrix[Double] = valid_fold(::, 0 to -2)
      val y_valid: DenseVector[Double] = valid_fold(::, -1)

      model.fit(x_train, y_train)

      val mae_score: Double = get_MAE(y_valid, model.predict(x_valid))
      println(s"MAE_score on the fold $i is: $mae_score")
    }
  }
}
