<script>
    import Menu from './Menu.svelte';
    import Footer from './Footer.svelte';

    // content components
    import Dashboard from './Dashboard.svelte';
    import Query from './Query.svelte';
    import Configuration from './Configuration.svelte';

	export let name;
    let selectedMenu = 1;



</script>

<div class="wrapper">
    <header>
        <span>Fennec Sahara</span>
    </header>
    <div class="container">
        <Menu bind:selectedMenu={selectedMenu}/>
        <div class="content">
            {#if selectedMenu === 1}
                <Dashboard/>
            {:else if selectedMenu === 2}
                <Query/>
            {:else if selectedMenu === 3}
                <Configuration/>
            {/if}
        </div>
    </div>
    <Footer/>
</div>

<style>
	.wrapper {

        height: 100%;
        width: 100%;
        flex-direction: column;
        background-color: var(--colC);
	}
	header {
	    font-family: 'Medula One', cursive;
        height: 68px;
        background-color: var(--colA);
        color: #ecf0f1;
        font-weight: bold;
        line-height: 68px;
        font-size: 30px;
        padding-left: 20px;
        border-bottom: 1px solid var(--colB);
    }
	.wrapper, .container {
        display: flex;
        flex: 1;
	}

    .container .content {
        flex: 1;
    }

</style>
