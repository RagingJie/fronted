<template>
	<div id="app">
		<div class="tableBox">
			<el-card shadow="always">
				<h1>【预约表 -- 蒸镀】小助手</h1>
				<el-form ref="form" :model="formData" label-width="130px">
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
						<el-date-picker type="date" placeholder="选择日期" v-model="formData.service_record.wx_reserve_date"
							style="width: 100%;"></el-date-picker>
					</el-form-item>
					<el-form-item label="请选择预约时段：">
						<el-checkbox-group v-model="formData.reserveTimesList" :max="3" :min="1">
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
					<el-form-item label="任务启动时间：">
						<el-time-picker arrow-control v-model="formData.taskStartTime" :picker-options="{
					      selectableRange: '00:00:00 - 23:59:59'
					    }" placeholder="任意时间点">
						</el-time-picker>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="onSubmit">开始任务</el-button>
						<el-button @click="resetParam">重置参数</el-button>
					</el-form-item>
				</el-form>
			</el-card>
		</div>
	</div>
</template>

<script>
	import axios from 'axios';
	export default {
		name: 'App',
		data() {
			return {
				formData: {
					// 微信用户id
					wx_session_user_id: 'YxuVWnZ0GzTdGk1r_iFPiw',
					// 服务id
					service_id: "68bafee6156d84e54d84ae55",
					// 预约参数
					service_record: {
						ekcmdfazkbhm: '徐康'
					},
					reserveTimesList: ['15-17', '17-19'],
					taskStartTime: new Date().setHours(9, 0, 1, 0)
				}
			}
		},
		methods: {
			// 重置参数
			resetParam() {
				this.formData = {
					// 服务id
					service_id: "68bafee6156d84e54d84ae55",
					// 预约参数
					service_record: {
						ekcmdfazkbhm: '徐康'
					},
					reserveTimesList: ['15-17', '17-19'],
					taskStartTime: new Date().setHours(9, 0, 1, 0)
				};
			},

			// 开始任务
			onSubmit() {
				this.reserveEvaporation(this.formData);
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
				})
			}

		}

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