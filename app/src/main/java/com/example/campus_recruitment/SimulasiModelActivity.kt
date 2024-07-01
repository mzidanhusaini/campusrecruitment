package com.example.campus_recruitment

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.tensorflow.lite.Interpreter
import android.widget.TextView
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiModelActivity : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModelPath = "model.tflite"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasi_model)
        var resultText : TextView = findViewById(R.id.id_result)
        var edtSalary : TextView = findViewById(R.id.id_salary)
        var edtGender: TextView = findViewById(R.id.id_gender)
        var edtSsc_p : TextView = findViewById(R.id.id_ssc_p)
        var edtSsc_b : TextView = findViewById(R.id.id_ssc_b)
        var edtHsc_p : TextView = findViewById(R.id.id_hsc_p)
        var edtHsc_b: TextView = findViewById(R.id.id_hsc_b)
        var edtHsc_s : TextView = findViewById(R.id.id_hsc_s)
        var edtDegree_p : TextView = findViewById(R.id.id_degree_p)
        var edtDegree_t : TextView = findViewById(R.id.id_degree_t)
        var edtWorkex: TextView = findViewById(R.id.id_workex)
        var edtEtest_p : TextView = findViewById(R.id.id_etest_p)
        var edtSpecialisation : TextView = findViewById(R.id.id_specialisation)
        var edtMba_p : TextView = findViewById(R.id.id_mba_p)
        var button : Button = findViewById(R.id.id_button)

        button.setOnClickListener{
            var result = doInference(
                edtSalary.text.toString(),
                edtGender.text.toString(),
                edtSsc_p.text.toString(),
                edtSsc_b.text.toString(),
                edtHsc_p.text.toString(),
                edtHsc_b.text.toString(),
                edtHsc_s.text.toString(),
                edtDegree_p.text.toString(),
                edtDegree_t.text.toString(),
                edtWorkex.text.toString(),
                edtEtest_p.text.toString(),
                edtSpecialisation.text.toString(),
                edtMba_p.text.toString(),
                )


            runOnUiThread{
                // ini dipakek pas udah ada model baru
                if (result == 0){
                    resultText.text = "Not Placed"
                }else {
                    resultText.text = "Placed"
                }
            }
        }
        initInterpreter()
    }

    private fun initInterpreter(){
        val options = Interpreter.Options()
        options.setNumThreads(5)
        options.setUseNNAPI(true)
        interpreter = Interpreter(loadModelfile(assets, mModelPath), options)
    }

    private fun  doInference(
        input1 : String,
        input2 : String,
        input3 : String,
        input4 : String,
        input5 : String,
        input6 : String,
        input7 : String,
        input8 : String,
        input9: String,
        input10 : String,
        input11 : String,
        input12 : String,
        input13 : String,

        ) : Int{
        val inputVal = FloatArray(13)

        inputVal[0] = input1.toFloat()
        inputVal[1] = input2.toFloat()
        inputVal[2] = input3.toFloat()
        inputVal[3] = input4.toFloat()
        inputVal[4] = input5.toFloat()
        inputVal[5] = input6.toFloat()
        inputVal[6] = input7.toFloat()
        inputVal[7] = input8.toFloat()
        inputVal[8] = input9.toFloat()
        inputVal[9] = input10.toFloat()
        inputVal[10] = input11.toFloat()
        inputVal[11] = input12.toFloat()
        inputVal[12] = input13.toFloat()

        val output = Array(1) { FloatArray(1) } // Adjusted to match the actual model output shape
        interpreter.run(inputVal, output)
        Log.e("result", output[0][0].toString())

        return if (output[0][0] > 0.5) 1 else 0
    }

    private fun loadModelfile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return  fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}