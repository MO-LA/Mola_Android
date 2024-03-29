package com.example.molaschoolproject.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.*
import com.example.molaschoolproject.adapter.ProfileAdapter
import com.example.molaschoolproject.data_type.SchoolData
import com.example.molaschoolproject.data_type.SchoolProfiles
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var schoolCategoryKind:TextView // 카테고리 학교유형
    lateinit var schoolCategoryFond: TextView // 카테고리 설립구분
    lateinit var schoolCategoryFondType: TextView // 카테고리 설립유형
    lateinit var schoolCategoryRegion: TextView // 카테고리 지역

    lateinit var editSearchMain: EditText // 메인 검색창
    lateinit var ivMainSearch: ImageView // 메인 검색 버튼

    private val service = CreateRetrofit().noHeaderRetrofit() // 레트로핏 서비스 생성

    private var profileList: List<SchoolProfiles>? = null // 메인 학교 리스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init() // 뷰 연결

        var rvMain = findViewById<RecyclerView>(R.id.rv_main) // 메인 리사이클러뷰
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.setHasFixedSize(true)

        getSchoolData(rvMain) // 메인리스트에 학교 리스트데이터 불러오기

        ivMainSearch.setOnClickListener {
            getSchoolDataByName(rvMain)
        }

        editSearchMain.setOnEditorActionListener{ textView, action, event -> // 키보드 엔터키 클릭이벤트
            var handled = false
            val info = EditorInfo.IME_ACTION_SEARCH
            Log.d("innn","innn $info")
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("Outt","outt")
                getSchoolDataByName(rvMain)
                handled = true
            }
            handled
        }

        schoolCategoryFond.setOnClickListener{ getSchoolDataByCategoryFond(rvMain) } // 학교 설립구분 선택 카테고리

        schoolCategoryFondType.setOnClickListener{ getSchoolDataByCategoryFondType(rvMain) } // 학교 설립유형 선택 카테고리

        schoolCategoryKind.setOnClickListener{ getSchoolDataByCategoryKind(rvMain) } // 학교 유형 선택 카테고리

        schoolCategoryRegion.setOnClickListener{ getSchoolDataByCategoryRegion(rvMain) } // 학교 지역 검색 카테고리

        val btnvMain = findViewById<BottomNavigationView>(R.id.btnv_main) // Main BottomNavigation
        btnvMain.findViewById<View>(R.id.btnv_item_wordsearch).setOnClickListener{
            val intent = Intent(this@MainActivity, WordSearchActivity::class.java)
            startActivity(intent)
        }
        btnvMain.findViewById<View>(R.id.btnv_item_community).setOnClickListener{
            val intent = Intent(this@MainActivity, CommunityActivity::class.java)
            startActivity(intent)
        }
        btnvMain.findViewById<View>(R.id.btnv_item_mypage).setOnClickListener{
            val intent = Intent(this@MainActivity,MyPageActivity::class.java)
            startActivity(intent)
        }

    }

    fun init() {
        schoolCategoryKind = findViewById(R.id.tv_schoolcatecory) // 카테고리 학교유형
        schoolCategoryFond = findViewById(R.id.tv_fond) // 카테고리 설립구분
        schoolCategoryFondType = findViewById(R.id.tv_fondtype) // 카테고리 설립유형
        schoolCategoryRegion = findViewById(R.id.tv_region) // 카테고리 지역

        editSearchMain = findViewById(R.id.edit_search_main) // 메인 검색창
        ivMainSearch = findViewById(R.id.iv_main_search) // 메인 검색 버튼

    }

    fun getSchoolData(rvMain: RecyclerView) {
        service.getSchoolData().enqueue(object: retrofit2.Callback<SchoolData>{
            override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                Log.d("Retrofitt","main code = ${response.code()}")
                if(response.isSuccessful) {
                    profileList = response.body()?.data
                    Log.d("Retrofitt","mainList = ${response.body()?.data}")
                    rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                }
            }

            override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                Log.d("Retrofitt","main False")
            }
        })
    }

    fun getSchoolDataByName(rvMain:RecyclerView) {
        var searchByNameData: String = editSearchMain.text.toString()
        if (searchByNameData.isEmpty())  searchByNameData = ""
        searchByNameData = searchByNameData.replace(" ","")
        service.getSchoolDataByName(q = searchByNameData).enqueue(object: retrofit2.Callback<SchoolData> {
            override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                Log.d("Retrofitt","searchByName main code = ${response.code()}")
                if(response.isSuccessful) {
                    profileList = response.body()?.data
                    rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                }
            }

            override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                Log.d("Retrofitt","searchByName main False")
            }
        })
    }

    fun getSchoolDataByCategoryFond(rvMain: RecyclerView) {
        val fondBottomSheet = SchoolCategoryFondBottomSheet()
        fondBottomSheet.show(supportFragmentManager,fondBottomSheet.tag)

        fondBottomSheet.setOnClickedListener(object : SchoolCategoryFondBottomSheet.textClickListener {
            override fun onClicked(fondText: String) {
                schoolCategoryFond.text = fondText
                schoolCategoryFondType.text = "설립유형"
                schoolCategoryKind.text = "학교유형"
                schoolCategoryRegion.text = "지역"
                var schoolFond = fondText

                if (schoolFond != "설립구분" && schoolFond != "전체") {
                    if (schoolFond == "국립") schoolFond = "NATIONAL"
                    else if (schoolFond == "사립") schoolFond = "PRIVATE"
                    else if (schoolFond == "공립") schoolFond = "PUBLIC"

                    service.getSchoolDataByFond(fond = schoolFond).enqueue(object : Callback<SchoolData> {
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","searchByFond main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","searchByFond main False")
                        }
                    })
                }
                else if (schoolFond == "전체") {
                    service.getSchoolData().enqueue(object: retrofit2.Callback<SchoolData>{
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                Log.d("Retrofitt","mainList = ${response.body()?.data}")
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","main False")
                        }
                    })
                }
            }
        })
    }

    fun getSchoolDataByCategoryFondType(rvMain: RecyclerView) {
        val bottomSheet = SchoolCategoryFondTypeBottomSheet()
        bottomSheet.show(supportFragmentManager,bottomSheet.tag)

        bottomSheet.setOnClickedListener(object  : SchoolCategoryFondTypeBottomSheet.textClickListener {
            override fun onClicked(fondTypeText: String) {
                schoolCategoryFondType.text = fondTypeText
                schoolCategoryFond.text = "설립구분"
                schoolCategoryKind.text = "학교유형"
                schoolCategoryRegion.text = "지역"

                var schoolFondType = fondTypeText
                if (schoolFondType != "설립유형" && schoolFondType != "전체") {
                    if (schoolFondType == "단설") schoolFondType = "INDEPENDENCE"
                    else if (schoolFondType == "병설") schoolFondType = "ESTABLISH"
                    else if (schoolFondType == "부속") schoolFondType = "ACCESSORIES"

                    service.getSchoolDataByFondType(fondType = schoolFondType).enqueue(object : Callback<SchoolData> {
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","searchByFondType main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","searchByFondType main False")
                        }
                    })
                }
                else if (schoolFondType == "전체") {
                    service.getSchoolData().enqueue(object: retrofit2.Callback<SchoolData>{
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                Log.d("Retrofitt","mainList = ${response.body()?.data}")
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","main False")
                        }
                    })
                }
            }
        })
    }

    fun getSchoolDataByCategoryKind(rvMain: RecyclerView) {
        val bottomSheet = SchoolCategoryKindBottomSheet()
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)

        bottomSheet.setOnClickedListener(object : SchoolCategoryKindBottomSheet.textClickListener {
            override fun onClicked(typeText: String) {
                schoolCategoryKind.text = typeText
                schoolCategoryFond.text = "설립구분"
                schoolCategoryFondType.text = "설립유형"
                schoolCategoryRegion.text = "지역"
                var schoolType = typeText
                if (schoolType != "학교유형" && schoolType != "전체") {
                    if (schoolType == "일반고") schoolType = "GENERAL"
                    else if (schoolType == "자율고") schoolType = "AUTONOMOUS"
                    else if (schoolType == "특성화고") schoolType = "SPECIALIZED"
                    else if (schoolType == "특수목적고") schoolType = "SPECIAL_PURPOSE"

                    service.getSchoolDataByKind(schoolKind = schoolType).enqueue(object : retrofit2.Callback<SchoolData> {
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","searchByKind main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","searchByKind main False")
                        }
                    })
                }
                else if (schoolType == "전체") {
                    service.getSchoolData().enqueue(object: retrofit2.Callback<SchoolData>{
                        override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                            Log.d("Retrofitt","main code = ${response.code()}")
                            if(response.isSuccessful) {
                                profileList = response.body()?.data
                                Log.d("Retrofitt","mainList = ${response.body()?.data}")
                                rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                            }
                        }

                        override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                            Log.d("Retrofitt","main False")
                        }
                    })
                }

            }
        })
    }

    fun getSchoolDataByCategoryRegion(rvMain: RecyclerView) {
        val bottomSheet = SchoolCategoryRegionBottomSheet()
        bottomSheet.show(supportFragmentManager,bottomSheet.tag)

        bottomSheet.setOnClickedListener(object : SchoolCategoryRegionBottomSheet.textClickListener {
            override fun onClicked(regionText: String) {
                var schoolRegion: String = regionText
                schoolCategoryFond.text = "설립구분"
                schoolCategoryFondType.text = "설립유형"
                schoolCategoryKind.text = "학교유형"
                schoolRegion = schoolRegion.replace(" ","")

                if (schoolRegion.isEmpty()) {
                    schoolCategoryRegion.text = "지역"
                    schoolRegion = ""
                }
                else schoolCategoryRegion.text = schoolRegion


                service.getSchoolDataByRegion(q = schoolRegion).enqueue(object : Callback<SchoolData> {
                    override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                        Log.d("Retrofitt","searchByRegion main code = ${response.code()}")
                        if(response.isSuccessful) {
                            profileList = response.body()?.data
                            rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                        }
                    }

                    override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                        Log.d("Retrofitt","searchByRegion main False")
                    }
                })
            }
        })
    }

    override fun onBackPressed() {

        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("앱을 종료 하시겠습니까?")
        dialog.setMessage("저희 MOLA을 이용해주셔서 감사합니다.")

        fun AppEnd() {
            ActivityCompat.finishAffinity(this)
        }

        var dialog_listener = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE ->
                        AppEnd()
                }
            }
        }
        dialog.setPositiveButton("YES", dialog_listener)
        dialog.setNegativeButton("NO", null)
        dialog.show()

    }
}