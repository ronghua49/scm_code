<template>
    <div>
        <div class="header">
            <div class="headleft">
                <span class="el-icon-tickets"></span>
                <span class="sx">数据列表</span>
            </div>
            <div class="headright">

            </div>
        </div>
        <el-table
            :data="tableData.rows"
            border size="small"
            style="width: 100%">
            <el-table-column
                v-for="column in columns"
                :prop="column.prop"
                :label="column.label"
                :key="column.prop"
                :fixed="column.fixed"
                :min-width="column.width" align="center">
                <el-table-column
                    v-for="item in column.child"
                    :prop="item.prop"
                    :label="item.label"
                    :key="item.prop"
                    :min-width="item.width" align="center">



                </el-table-column>

            </el-table-column>
            <el-table-column label="历史订单" align="center">
                <el-table-column
                    v-for="item in columns3"
                    :prop="item.prop"
                    :label="item.label"
                    :key="item.prop"
                    :min-width="item.width" align="center">


                    <template slot-scope="scope" v-if="item.prop=='lishi2'||item.prop=='lishi3'">
                        <el-input size="mini" v-if="item.prop=='lishi2'" v-model="scope.row.lishi2"></el-input>
                        <el-input size="mini" v-if="item.prop=='lishi3'" v-model="scope.row.lishi3"></el-input>
                    </template>




                </el-table-column>

            </el-table-column>
            <el-table-column :label="'最新需求计划'+jinTian" align="center">
                <el-table-column
                    v-for="item in columns4"
                    :prop="item.prop"
                    :label="item.label"
                    :key="item.prop"
                    :min-width="item.width" align="center">


                    <template slot-scope="scope" v-if="item.prop=='xuqiu1'||item.prop=='xuqiu2'||item.prop=='xuqiu3'">
                        <el-input size="mini" v-if="item.prop=='xuqiu1'" v-model="scope.row.xuqiu1"></el-input>
                        <el-input size="mini" v-if="item.prop=='xuqiu2'" v-model="scope.row.xuqiu2"></el-input>
                        <el-input size="mini" v-if="item.prop=='xuqiu3'" v-model="scope.row.xuqiu3"></el-input>
                    </template>




                </el-table-column>

            </el-table-column>

            <el-table-column
                v-for="column in columns2"
                :prop="column.prop"
                :label="column.label"
                :key="column.prop"
                :fixed="column.fixed"
                :min-width="column.width" align="center">
                <el-table-column
                    v-for="item in column.child"
                    :prop="item.prop"
                    :label="item.label"
                    :key="item.prop"
                    :min-width="item.width" align="center">



                </el-table-column>

            </el-table-column>

        </el-table>


    </div>
</template>

<script>
    export default {
        name: "cgProcurementPlan",
        data() {
            return {
                tableData: {},
                columns: [
                    {
                        prop: "chanpin", width: '600', label: "产品信息", fixed: "left", child: [
                            {prop: "medicineType", label: "分类", width: "120",}, {
                                prop: "productNum",
                                label: "物料编码",
                                width: "120",
                            },
                            {prop: "inventoryCode", label: "存货代码", width: "180",}, {
                                prop: "productName",
                                label: "存货名称",
                                width: "180",
                            }
                        ]
                    },

                    {prop: "anquankucunliang", label: "安全库存量（最低库存量）", width: "120",},




                ],
                columns2:[
                    {

                        label: "供工厂参考",
                        width: "",
                        child: [
                            {prop: "caokao1", label: "2019.5月", width: "120",}, {
                                prop: "caokao2",
                                label: "2019年6月",
                                width: "120",
                            },
                            {prop: "caokao3", label: "2019年7月", width: "120",}, {
                                prop: "caokao4",
                                label: "2019年8月",
                                width: "120",
                            },
                            {prop: "caokao5", label: "2019年9月", width: "120",}, {
                                prop: "caokao6",
                                label: "2019年10月",
                                width: "120",
                            },
                            {prop: "caokao7", label: "2019年11月", width: "120",}, {
                                prop: "caokao8",
                                label: "2019年12月",
                                width: "120",
                            }, {prop: "caokao9", label: "", width: "120",},
                        ]
                    },
                    {prop: "heji2019", label: "2019年订单合计", width: "120",},
                    {prop: "wanchenglv2019", label: "2019年订单合计", width: "120",},

                    {prop: "piliang", label: "批量", width: "120",},
                    {

                        label: "预计库销比TX",
                        width: "",
                        child: [
                            {prop: "TX1", label: "12月初", width: "120",}, {prop: "TX2", label: "1月初", width: "120",},
                            {prop: "TX3", label: "2月初", width: "120",}, {prop: "TX4", label: "3月初", width: "120",},
                            {prop: "TX5", label: "4月初", width: "120",}, {prop: "TX6", label: "5月初", width: "120",},
                            {prop: "TX7", label: "6月初", width: "120",}, {prop: "TX8", label: "7月初", width: "120",},
                            {prop: "TX9", label: "8月初", width: "120",}, {prop: "TX10", label: "9月初", width: "120",},
                            {prop: "TX11", label: "10月初", width: "120",}, {prop: "TX12", label: "11月初", width: "120",},
                        ]
                    },
                ],
                columns3:[],
                columns4:[],
                jinTian:''
            }
        },
        created() {
            this.setColumn();
        },
        mounted() {
            this.findPage();
            this.jiSum();
        },
        methods: {

            setColumn() {
                var nowdays = new Date();
                var year = nowdays.getFullYear();
                var date  = nowdays.getDate();
                var month = nowdays.getMonth()+1;
                console.log(month);
                var lastMonth = nowdays.getMonth();
                if (lastMonth == 0) {
                    lastMonth = 12;
                    year = year - 1;
                }
                if (lastMonth < 10) {
                    lastMonth = "0" + lastMonth;
                }
                var myDate = new Date(year, lastMonth, 0);
                var lastDay = year + "." + lastMonth + "." + myDate.getDate();//上个月的最后一天

                this.jinTian = month + '.' + date;

                let obj1 = {
                    label: "总库存量" + lastDay, width: "120", child: [
                        {prop: "xiancunhuoliang", label: "现库存量", width: "120",}, {
                            prop: "yikaichuan",
                            label: "已开船",
                            width: "120",
                        },
                        {prop: "xiancunKaichuan", label: "现存+开船", width: "120",},
                    ]
                };
                let obj2=[
                            {prop: "lishi1", label: "未供应订单截止"+lastDay, width: "120",}, {
                                prop: "lishi2",
                                label: this.jiSum(),
                                width: "120",
                            },
                            {prop: "lishi3", label: this.jiSum(+1), width: "120",}
                    ];
                 let obj3 =  [
                            {prop: "xuqiu1", label: this.jiSum(+2), width: "120",}, {
                                prop: "xuqiu2",
                                label: this.jiSum(+3),
                                width: "120",
                            },
                            {prop: "xuqiu3", label: this.jiSum(+4), width: "120",},

                        ];

                let obj4 = {
                        prop: "",
                        label: "发货预测DA（滚动6个月）",
                        width: "",
                        child: [
                            {prop: "fahuo1", label: this.jiSum(), width: "120",}, {
                                prop: "fahuo2",
                                label: this.jiSum(+1),
                                width: "120",
                            },
                            {prop: "fahuo3", label: this.jiSum(+2), width: "120",}, {
                                prop: "fahuo4",
                                label: this.jiSum(+3),
                                width: "120",
                            },
                            {prop: "fahuo5", label: this.jiSum(+4), width: "120",}, {
                                prop: "fahuo6",
                                label: this.jiSum(+5),
                                width: "120",
                            },

                        ]
                    };

                var obj5 = {

                        label: "实际发货",
                        width: "",
                        child: [
                            {prop: "MAT12", label: "月均发货（MAT12）", width: "120",}, {
                                prop: "MAT6",
                                label: "月均发货（MAT6）",
                                width: "120",
                            },
                            {prop: "MAT3", label: "月均发货（MAT3）", width: "120",},
                            {
                                prop: "shiji1",
                                label: this.jiSum(-3),
                                width: "120",
                            },
                            {prop: "shiji2", label: this.jiSum(-2), width: "120",},
                            {
                                prop: "shiji3",
                                label: this.jiSum(-1),
                                width: "120",
                            }
                        ]
                    };

                this.columns.splice(0, 0, obj1);
                this.columns3 = obj2;
                this.columns4 = obj3;
                this.columns.splice(1, 0, obj5);
                this.columns.splice(2, 0, obj4);


                // console.log(this.columns);
                // console.log(month);
            },
            jiSum(change){
                change = change? change:0
                var nowdays = new Date();
                var year = nowdays.getFullYear();
                var month = nowdays.getMonth()+1;

                var sum = month+change
                if (sum>12) {
                    sum = sum-12;
                    year=year+1;
                }else if (sum<1) {
                    sum = sum+12;
                    year=year-1;
                }

                return year + '年' + sum + '月';
            },

            findPage() {
                this.$api.base.postScm({
                    url: "/scm/scmZsjProduct/v1/firstList",
                    data: {}
                }).then(res => {
                    // console.log(res);


                    res.rows.map(function (v, i) {
                        v.xiancunhuoliang = 699998;
                        v.yikaichuan = 499999;
                        v.xiancunKaichuan = 6588860;
                        v.anquankucunliang = 589986;
                        v.xiankucunliang = 3699999;
                        v.fahuo1 = 459999;
                        v.fahuo2 = 479999;
                        v.fahuo3 = 499999;
                        v.fahuo4 = 599999;
                        v.fahuo5 = 399999;
                        v.fahuo6 = 478885;
                        v.MAT12 = 69920;
                        v.MAT6 = 69920;
                        v.MAT3 = 69920;
                        v.shiji1 = 104480;
                        v.shiji2 = 236880;
                        v.shiji3 = 246720;
                        v.lishi1 = 456660;
                        v.lishi2 = 560000;
                        v.lishi3 = 578830;
                        v.xuqiu1 = 488988;
                        v.xuqiu2 = 799999;
                        v.xuqiu3 = 688890;
                        v.caokao1 = 2477789;
                        v.caokao2 = 3378980;
                        v.caokao3 = 2348930;
                        v.caokao4 = 4343783;
                        v.caokao5 = 6899934;
                        v.caokao6 = 6546454;
                        v.caokao7 = 5464564;
                        v.caokao8 = 7898790;
                        v.caokao9 = 4545345;

                    })
                    this.tableData = res
                })
            }
        }
    }
</script>

<style scoped lang="scss">
    .header {
        width: 100%;
        height: 38px;
        font-size: 16px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        background: #f3f3f3;
    }

    .headleft {
        margin-left: 10px;
    }

    .headright {
        margin-right: 10px;
    }
</style>
