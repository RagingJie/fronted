<template>
	<div class="home">

		<img src="@/assets/image/logo.png" alt="Vue logo" />
		<img src="@/assets/image/1.jpg" alt="1" />
		<img src="@/assets/image/2.jpg" alt="2" />
		<img src="@/assets/image/3.jpg" alt="3" />
		<img src="@/assets/image/4.jpg" alt="4" />

		<span>测试测试</span><br /><br /><br />

		<el-image :src="require('@/assets/image/logo.png')" alt="Vue logo" />
		<el-image :src="require('@/assets/image/1.jpg')" alt="1" />
		<el-image :src="require('@/assets/image/2.jpg')" alt="2" />
		<el-image :src="require('@/assets/image/3.jpg')" alt="3" />
		<el-image :src="require('@/assets/image/4.jpg')" alt="4" />

	</div>
</template>

<script>
	export default {
		name: 'HomeView',
		components: {}
	}
</script>