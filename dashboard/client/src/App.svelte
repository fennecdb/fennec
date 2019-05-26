<script>
	export let name;

	let menuItems = [
        { id: 'a', name: 'Dashboard', faIcon: "fas fa-chart-area"},
        { id: 'c', name: 'Query', faIcon: "fas fa-search" },
        { id: 'b', name: 'Configuration', faIcon: "fas fa-sliders-h"},
    ];
</script>

<div class="wrapper">
    <header>
        <span>Fennec Sahara</span>
    </header>
    <div class="container">

        <ul class="menu">
            {#each menuItems as item}
                <li>
                    <i class="icon {item.faIcon}"></i>
                    <span>{item.name}</span>
                </li>
            {/each}
        </ul>
        <div class="content">
        </div>
    </div>
    <footer>
        <ul>
            <li>FennecDB</li>
            <li>Credits</li>
            <li>Github</li>
        </ul>
    </footer>
</div>

<style>
	.wrapper {
	    --colZ: #ecf0f1;
        --colA: #fcbb6d;
        --colB: #d8737f;
        --colC: #ab6c82;
        --colD: #685d79;
        --colE: #475c7a;
        height: 100%;
        width: 100%;
        flex-direction: column;
        background-color: #ecf0f1;
	}
	header {
        height: 68px;
        background-color: var(--colA);
        color: #ecf0f1;
        font-weight: bold;
        line-height: 68px;
        font-size: 20px;
        padding-left: 20px;
        border-bottom: 1px solid var(--colB);
    }
	.wrapper, .container {
        display: flex;
        flex: 1;
	}
    .container ul {
        /*background-color: #f39c12;*/
        background-color: var(--colB);
        color: #ecf0f1;
        list-style: none;
        margin: 0;
        padding: 20px 0 20px 0;
        display: flex;
        flex-direction: column;
        width: 100px;
    }
    .container ul li {
        height: 64px;
        width: 80px;
        margin-left: calc(50px - 45px);
        text-align:center;
        cursor: pointer;
        border-top: 1px solid rgba(255, 255, 255, 0.1);
        border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        margin-bottom: 10px;
        padding: 4px 5px 0px 5px;
    }
    .container ul li:hover {
        background-color: rgba(255, 255, 255, 0.2);
    }
    .container ul li .icon {
        font-size: 24px;
        height: 24px;
        width: 24px;
        padding-top: 5px;
    }
    .container ul li span {
        display:block;
        padding-top: 5px;
        font-size: 12px;
    }
    .container .content {
        flex: 1;
    }
    footer {
        background-color: var(--colE);
        height: 40px;
        color: var(--colZ);
        border-top: 1px solid var(--colD);
    }
    footer ul {
        list-style: none;
    }
    footer li {
        float: left;
    }
</style>
