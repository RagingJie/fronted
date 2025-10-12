<template>
	<div id="app">
		<div class="tableBox">
			<el-card shadow="always">
				<h1>【预约表 -- 蒸镀】小助手</h1>
				<el-form ref="ruleForm" :model="formData" label-width="150px">
					<el-form-item label="微信用户ID：">
						<el-input v-model="formData.wx_session_user_id" placeholder="请输入微信用户ID" clearable></el-input>
					</el-form-item>
					<el-form-item label="服务ID：">
						<el-input v-model="formData.service_id" disabled></el-input>
					</el-form-item>
					<el-form-item label="预约人姓名：">
						<el-input v-model="formData.service_record.ekcmdfazkbhm" placeholder="请输入预约人姓名"
							clearable></el-input>
					</el-form-item>
					<el-form-item label="请选择预约日期：">
						<el-date-picker type="date" placeholder="选择日期" v-model="reserveDate"
							style="width: 100%;" :picker-options="pickerOptions"></el-date-picker>
					</el-form-item>
					<el-form-item label="请选择预约时段：">
						<el-checkbox-group v-model="formData.reserveTimesList">
							<!-- 第一行 -->
							<el-row>
								<el-col :span="6"><el-checkbox label="7.30-8.30" name="6zund8ga"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="8.30-10.30"
										name="ygw8b2td"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="10.30-12.00"
										name="tr5vgygw"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="12-13" name="nyokqqf9"></el-checkbox></el-col>
							</el-row>
							<!-- 第二行 -->
							<el-row>
								<el-col :span="6"><el-checkbox label="13-15" name="1vjc5rkn"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="15-17" name="rrjy50as"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="17-19" name="b2pr09kj"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="19-21" name="ulg7wwgd"></el-checkbox></el-col>
							</el-row>
							<!-- 第三行 -->
							<el-row>
								<el-col :span="6"><el-checkbox label="21-22" name="ri6mnmbp"></el-checkbox></el-col>
								<el-col :span="6"><el-checkbox label="22-23" name="4i06d63h"></el-checkbox></el-col>
							</el-row>
						</el-checkbox-group>
					</el-form-item>
					<el-form-item label="任务开始时间：">
						<el-time-picker v-model="formData.taskStartTime" :picker-options="{
					      selectableRange: '00:00:00 - 23:59:59'
					    }" placeholder="任意时间点">
						</el-time-picker>
					</el-form-item>
					<el-form-item label="任务结束时间：">
						<el-time-picker v-model="formData.taskEndTime" :picker-options="{
					      selectableRange: '00:00:10 - 23:59:59'
					    }" placeholder="任意时间点">
						</el-time-picker>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="onSubmit" :loading="submitTaskLoading" round>开始任务</el-button>
						<el-button type="danger" @click="resetParam" round>重置参数</el-button>
						<el-button type="success" @click="seeResult" :disabled="isSeeResult" round>查看结果</el-button>
					</el-form-item>
				</el-form>
				<div style="display: flex; gap: 20px; width: 100%;">
					<el-card shadow="hover" style="width: 100%;margin-top: 20px; flex: 1; min-width: 0; "
						v-if="isShowTaskCountdown">
						<div style="width: 100%; display: inline-block; ">
							<el-statistic @finish="hilarity" :value="taskCountdown" time-indices title="🚩任务开始执行倒计时...">
							</el-statistic>
						</div>
					</el-card>
					<el-card shadow="hover" style="width: 100%;margin-top: 20px; flex: 1; min-width: 0;"
						v-if="isShowTaskCountdown2">
						<div style="width: 100%; display: inline-block; ">
							<el-statistic @finish="endTask" :value="taskCountdown2" time-indices title="🎉任务执行中....">
							</el-statistic>
						</div>
					</el-card>
				</div>
			</el-card>


			<el-dialog title="预约结果" :visible.sync="dialogVisible" width="60%" :close-on-click-modal="false"
				:show-close="false" top="1vh">
				<el-card shadow="always" style="width: 100%">
					<el-table :data="reserveResult" border max-height="600">
						<el-table-column label="序号" type="index" width="75" align="center"></el-table-column>
						<el-table-column label="预约日期" prop="reserveDate" align="center"></el-table-column>
						<el-table-column label="预约时段" prop="timePeriod" align="center"></el-table-column>
						<el-table-column label="预约结果" prop="reserveResult" align="center">
							<template slot-scope="scope">
								<el-tag v-if="scope.row.reserveResult == 1" type="success">预约成功</el-tag>
								<el-tag v-else type="danger">预约失败</el-tag>
							</template>
						</el-table-column>
						<el-table-column label="预约反馈信息" prop="msg" align="center" width="280"></el-table-column>
					</el-table>
				</el-card>
				<span slot="footer" class="dialog-footer">
					<el-button type="primary" @click="dialogVisible = false">确 定</el-button>
				</span>
			</el-dialog>

		</div>
	</div>
</template>

<script>
	import axios from 'axios';
	export default {
		name: 'App',
		data() {
			return {
				reserveDate: '',
				taskCountdown2: 0,
				isShowTaskCountdown2: false,
				// 是否展示任务倒计时
				isShowTaskCountdown: false,
				// 任务倒计时
				taskCountdown: 0,
				dialogVisible: false,
				// 预约结果
				reserveResult: [],
				// 提交任务加载
				submitTaskLoading: false,
				// 能否查看结果
				isSeeResult: true,
				formData: {
					// 服务id
					service_id: "68bafee6156d84e54d84ae55",
					// 预约参数
					service_record: {
						ekcmdfazkbhm: '徐康'
					},
					reserveTimesList: ['15-17', '17-19'],
					taskStartTime: new Date().setSeconds(1, 0),
					taskEndTime: new Date().setSeconds(10, 0)
				},
				pickerOptions: {
					disabledDate(time) {
						// 获取当前日期（本地时间）
						const today = new Date();
						// 计算允许的起始日期（今日0点）
						const start = new Date(today.getFullYear(), today.getMonth(), today.getDate());
						// 计算允许的结束日期（大后天0点，即后天23:59:59的下一秒）
						const end = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 3);
						// 禁用不在[今日, 后天]范围内的日期
						return time < start || time >= end;
					}
				}
			}
		},
		methods: {

			taskStart() {
				this.$notify({
					title: "任务开始",
					message: "任务已开始，待执行....",
					type: 'success',
					duration: 0
				});
			},

			hilarity() {
				this.$notify({
					title: "任务执行",
					message: "任务正在执行....",
					type: 'success',
					duration: 0
				});
				this.isShowTaskCountdown2 = true
				this.taskCountdown2 = Date.now() + (this.formData.taskEndTime - new Date())
			},

			endTask() {
				this.submitTaskLoading = false;
				this.isSeeResult = false;
				this.$notify({
					title: "任务完成",
					message: "任务已执行完毕，点击【查看结果】按钮，查看结果！",
					type: 'success',
					duration: 0
				});
			},

			errorTip(msg) {
				this.isShowTaskCountdown2 = false;
				this.isShowTaskCountdown = false;
				this.submitTaskLoading = false;
				this.dialogVisible = false;
				this.isSeeResult = true;
				this.$notify({
					title: "错误",
					message: msg,
					type: 'error',
					duration: 0
				});
			},

			// 重置参数
			resetParam() {
				this.isShowTaskCountdown2 = false;
				this.isShowTaskCountdown = false;
				this.submitTaskLoading = false;
				this.dialogVisible = false;
				this.isSeeResult = true;
				this.reserveDate = ''
				this.formData = {
					// 服务id
					service_id: "68bafee6156d84e54d84ae55",
					// 预约参数
					service_record: {
						ekcmdfazkbhm: '徐康'
					},
					reserveTimesList: ['15-17', '17-19'],
					taskStartTime: new Date().setSeconds(1, 0),
					taskEndTime: new Date().setSeconds(10, 0)
				};
			},

			// 开始任务
			onSubmit() {
				if (this.formData.wx_session_user_id == null || this.formData.wx_session_user_id == undefined) {
					this.$message.error('微信用户ID不能为空！ 请填写~');
					return;
				}
				const serviceRecord = this.formData.service_record;
				if (serviceRecord.ekcmdfazkbhm == null || serviceRecord.ekcmdfazkbhm == undefined || serviceRecord
					.ekcmdfazkbhm == '') {
					this.$message.error('预约人姓名不能为空！ 请填写~');
					return;
				}
				if (this.reserveDate == null || this.reserveDate == undefined) {
					this.$message.error('预约日期不能为空！ 请填写~');
					return;
				}
				if (this.formData.reserveTimesList == null || this.formData.reserveTimesList.length == 0) {
					this.$message.error('预约时段不能为空！ 请填写~');
					return;
				}
				if (this.formData.taskStartTime == null || this.formData.taskStartTime == undefined) {
					this.$message.error('任务开始时间不能为空！ 请填写~');
					return;
				}
				if (this.formData.taskEndTime == null || this.formData.taskEndTime == undefined) {
					this.$message.error('任务结束时间不能为空！ 请填写~');
					return;
				}
				if (this.formData.taskStartTime < new Date()) {
					this.$message.error('《任务开始时间》必须【大于】当前时间！');
					return;
				}
				if (this.formData.taskEndTime <= this.formData.taskStartTime) {
					this.$message.error('《任务结束时间》必须【大于】《任务开始时间》！');
					return;
				}
				if ((this.formData.taskEndTime - this.formData.taskStartTime) <= 6000) {
					this.$message.error('《任务间隔时间》必须【大于】6秒！');
					return;
				}
				this.isShowTaskCountdown = true;
				this.taskCountdown = Date.now() + (this.formData.taskStartTime - new Date());
				this.submitTaskLoading = true;
				this.isSeeResult = true;
				this.taskStart();
				this.formData.service_record.wx_reserve_date = this.formatDate(this.reserveDate);
				this.reserveEvaporation(this.formData);
			},

			// 查看预约结果
			seeResult() {
				this.dialogVisible = true
			},

			// 格式化为 yyyy-MM-dd HH:mm:ss
			formatDate(date) {
				const year = date.getFullYear();
				const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始
				const day = String(date.getDate()).padStart(2, '0');
				const hours = String(date.getHours()).padStart(2, '0');
				const minutes = String(date.getMinutes()).padStart(2, '0');
				const seconds = String(date.getSeconds()).padStart(2, '0');
				return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
			},

			// 预约 -- 蒸镀
			reserveEvaporation(data) {
				const url = "http://127.0.0.1:8848/api/reserve/reserveEvaporation"
				this.$ajax.post(
					url,
					JSON.stringify(data), {
						headers: {
							'Content-Type': 'application/json'
						}
					}).then(res => {
					console.info('预约结果为====>', res.data)
					if (res.data.code == 200) {
						this.reserveResult = res.data.data;
					} else if (res.data.code == 500) {
						this.$message.error(res.data.message);
						this.errorTip(res.data.message);
					} else {
						this.errorTip('系统错误，请联系开发人员解决！！！');
					}
				}).catch(error => {
					// 网络错误或服务器无响应（如连接超时、DNS失败、断网等）
					if (!error.response) {
						this.$message.error('网络错误或无法连接服务器，请检查网络连接或服务器是否启动，检查后再试！');
						this.errorTip('网络错误或无法连接服务器，请检查网络连接或服务器是否启动，检查后再试！');
					} else {
						// 服务器有响应，但状态码不是 2xx（如 404, 502, 503 等）
						const status = error.response.status;
						let msg = `请求失败（状态码：${status}）`;
						switch (status) {
							case 400:
								msg = '请求参数错误，请检查输入内容';
								break;
							case 401:
								msg = '未授权，请重新登录';
								break;
							case 403:
								msg = '禁止访问';
								break;
							case 404:
								msg = '请求的接口不存在';
								break;
							case 500:
								msg = '服务器内部错误';
								break;
							case 502:
							case 503:
							case 504:
								msg = '服务暂时不可用，请稍后再试';
								break;
							default:
								msg = `请求失败（${status}）`;
						}
						this.$message.error(msg);
						this.errorTip(msg);
					}
				});
			}
		},

		mounted() {
			document.title = "【预约表 -- 蒸镀】小助手";
		},

	}
</script>

<style>
	.tableBox {
		/* 黑色实线边框 */
		border: 2px solid #000000;
		/* 上下边距20px，左右自动居中 */
		margin: 60px auto;
		/* 内边距让内容不贴边 */
		padding: 20px;
		/* 限制宽度为父容器的80% */
		width: 70%;
		/* 最大宽度限制（可选） */
		max-width: 800px;
		/* 边框和内边距包含在宽度内 */
		box-sizing: border-box;
	}
</style>