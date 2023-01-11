<template>
    <div class="page-loading" v-if="chargement">
        <div class="loader"></div>
    </div>
</template>
<script>
export default {
    props: {
        chargement: {
            type: Boolean,
            default: true
        }
    },
}
</script>

<style scoped>
.page-loading {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    height: 100vh;
    width: 100vw;
    top: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.9);
    z-index: 60;
    transition: 5s;
    font-size: 36px;
    color: white;
}
.loader {
    display: block;
    position: absolute;
    width: 150px;
    height: 150px;
    border-left: 3px solid rgb(83, 83, 199);
    border-radius: 100px;
    animation: spin 2s linear infinite;
}
.loader:after {
    content: '';
    position: absolute;
    top: 12px;
    left: 12px;
    right: 12px;
    bottom: 12px;
    border-radius: 100px;
    border-top: 3px solid rgb(174, 77, 253);
}
.loader:before {
    content: '';
    position: absolute;
    top: 3px;
    left: 3px;
    right: 3px;
    bottom: 3px;
    border-radius: 100px;
    border-right: 3px solid var(--magenta);
}
@keyframes spin {
    from {
        transform: rotate(0deg);
    } 
    to {
        transform: rotate(360deg);
    }
}
</style>